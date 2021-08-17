package Actions;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CountExample {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("third").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<String> countries = Arrays.asList("iran", "america", "afghanestan", "poland", "spania","iran","suadi");
        JavaRDD<String> mainRdd = sparkContext.parallelize(countries);

        System.out.println(mainRdd.count());

        Map<String,Long> wordCountByValues = mainRdd.countByValue();
        for (Map.Entry<String,Long> item:wordCountByValues.entrySet()){
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }
}
