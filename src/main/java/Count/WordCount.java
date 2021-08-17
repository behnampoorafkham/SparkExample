package Count;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf sparkConf = new SparkConf().setAppName("first").setMaster("local[3]");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<String> mainRdd = javaSparkContext.textFile("src/main/resources/word_count.text");
        JavaRDD<String> rdd1 = mainRdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        Map<String, Long> wordcount = rdd1.countByValue();
        for (Map.Entry<String, Long> item : wordcount.entrySet()) {
            System.out.println(item.getKey() + " " + item.getKey());
            }
        }
    }
