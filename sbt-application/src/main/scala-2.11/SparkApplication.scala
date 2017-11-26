import org.apache.spark.sql.types._
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.sql.functions.split
import org.apache.spark.sql.functions.explode
import org.apache.spark.sql.functions.regexp_replace
import org.apache.spark.sql.functions.lower

/**
  * Sample Spark application.
  *  Calculates volume of a sphere (r = 1) using the Monte Carlo method.
  */
object SparkApplication {

  case class Point(x: Double, y: Double, z: Double)

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Spark Pi")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    val reviews = "/tmp/data/Reviews.csv" 

    val ds = spark.read.format("csv").option("header", "true").load(reviews);
    ds.createOrReplaceTempView("Review")

    spark.sql("select UserId, count(*) as count from Review group by UserId order by count desc").limit(20).show();
   
    spark.sql("select ProductId, count(*) as count from Review group by ProductId order by count desc").limit(20).show();

    
    val ds1 = ds.withColumn("Text", lower(regexp_replace($"Text", "(!|\\)|\\(|,|\"|\\.)", ""))).withColumn("Text", split($"Text", "\\s+")).select(explode($"Text"));
    ds1.createOrReplaceTempView("Words")

    spark.sql("select col as word, count(*) as count from Words group by col order by count desc").limit(20).show();

    spark.stop()
  }
}
