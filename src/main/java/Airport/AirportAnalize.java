package Airport;

import org.apache.ivy.util.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.Utils;
import org.apache.spark.util.Utils$;

import java.util.Arrays;
import java.util.Map;

public class AirportAnalize {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("second").setMaster("local[3]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaRDD<String> mainRdd = sparkContext.textFile("src/main/resources/airports.text");

        JavaRDD<String> airportCanada = mainRdd.filter(line -> (line.split(",")[3]).equals("\"United States\""));

        Map<String, Long> x = airportCanada.countByValue();
        System.out.println(x.size());
        JavaRDD<String> rdd =  airportCanada.map(line->{
            String[] city = line.split(",");
            return StringUtils.join(new String[]{city[0],city[1]},"/*/");
        });
        rdd.saveAsTextFile("out/airportAfter.text");
    }
}
