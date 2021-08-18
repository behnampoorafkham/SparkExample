import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;

import java.util.Arrays;
import java.util.List;

public class Sparkpersist {
    public static void main(String[] args) {
        
        SparkConf sparkConf = new SparkConf().setAppName("seventh").setMaster("local[*]");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        List<String> countries = Arrays.asList("iran", "america", "afghanestan", "poland", "spania");
        JavaRDD<String> mainRdd = sparkContext.parallelize(countries);

        // priority based on speed
        mainRdd.persist(StorageLevel.MEMORY_ONLY());
        mainRdd.persist(StorageLevel.MEMORY_ONLY_SER());
        mainRdd.persist(StorageLevel.MEMORY_AND_DISK());
        mainRdd.persist(StorageLevel.MEMORY_AND_DISK_SER());

    }
}
