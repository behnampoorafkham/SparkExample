package PairRdd.filter;

import com.sun.jersey.server.impl.cdi.Utils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class AirportsNotInUsaSolution {

    public static void main(String[] args) throws Exception {

        SparkConf conf = new SparkConf().setAppName("tenth").setMaster("local[*]");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> airportsRDD = sc.textFile("src/main/resources/airports.text");

        JavaPairRDD<String, Integer> airportPairRDD = airportsRDD.mapToPair(getAirportNameAndCountryNamePair());

        JavaPairRDD<String, Integer> airportsNotInUSA = airportPairRDD.filter(keyValue -> !keyValue._1().equals("\"United States\""));

        airportsNotInUSA.saveAsTextFile("out/airports_not_in_usa_pair_rdd.text");
    }

    private static PairFunction<String, String, Integer> getAirportNameAndCountryNamePair() {
        return (PairFunction<String, String, Integer>) line -> new Tuple2<>(line.split(",")[3],
                                                                           Integer.valueOf(line.split(",")[0]));
    }
}
