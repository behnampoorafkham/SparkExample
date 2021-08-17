package Actions;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class collectExample {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("sixth").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<String> countries = Arrays.asList("iran","america","afghanestan","poland","spania");
        JavaRDD<String> mainRdd = sparkContext.parallelize(countries);
        List<String> words = mainRdd.collect();
        for (String item : words){
            System.out.println(item);
        }
    }
}
