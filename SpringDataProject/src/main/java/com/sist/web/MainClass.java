package com.sist.web;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Configuration conf=new Configuration();
			// hadoop을 불러온다
			// hadoop => 여러 유저가 수집한 데이터를 저장하는 장소 => 1~1000 700
			Job job=Job.getInstance(conf,"WordCount");
			job.setJarByClass(MainClass.class);
			job.setMapperClass(WordMapper.class);
			job.setReducerClass(WordReducer.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.addInputPath(job, new Path("./input.txt"));
			FileOutputFormat.setOutputPath(job, new Path("./output"));
			
			job.waitForCompletion(true);
		}catch (Exception ex) {}
	}

}
