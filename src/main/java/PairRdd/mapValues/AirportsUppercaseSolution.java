package PairRdd.mapValues;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class AirportsUppercaseSolution {

    public static void main(String[] args) throws Exception {

        SparkConf conf = new SparkConf().setAppName("fourth").setMaster("local");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsRDD = sc.textFile("src/main/resources/airports.text");

        JavaPairRDD<String, String> airportPairRDD = airportsRDD.mapToPair(getAirportNameAndCountryNamePair());

        JavaPairRDD<String, String> upperCase = airportPairRDD.mapValues(countryName -> countryName.toUpperCase());

        upperCase.saveAsTextFile("out/airports_uppercase.text");
    }

    private static PairFunction<String, String, String> getAirportNameAndCountryNamePair() {
        return (PairFunction<String, String, String>) line -> new Tuple2<>(line.split(",")[1],
                                                                           line.split(",")[3]);
    }
}
