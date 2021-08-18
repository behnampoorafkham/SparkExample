package PairRdd.groupbykey;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AirportsByCountrySolution {

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("eleventh").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> list = Arrays.asList("1,behnam","2,ali","1,reza","3,mona","1,ahmad","3,vahid");
        JavaRDD<String> lines = sc.parallelize(list);

        JavaPairRDD<String, String> countryAndAirportNameAndPair =
                lines.mapToPair( airport -> new Tuple2<>(airport.split(",")[0],
                                                         airport.split(",")[1]));

        JavaPairRDD<String,Iterable<String>> airportsByCountry = countryAndAirportNameAndPair.groupByKey();
        for (Map.Entry<String, Iterable<String>> airports : airportsByCountry.collectAsMap().entrySet()) {
            System.out.println(airports.getKey() + " : " + airports.getValue());
        }
    }
}
