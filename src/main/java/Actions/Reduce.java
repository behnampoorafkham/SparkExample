package Actions;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class Reduce {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("fifth").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<Integer> countries = Arrays.asList(14, 61, -14, 51, 12,86,-20);
        JavaRDD<Integer> mainRdd = sparkContext.parallelize(countries);

        Integer concat = mainRdd.reduce((r1,r2)->r1+r2);
        System.out.println(concat);
    }
}
