package com.sist.web;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
// 1,1,1,1,1,1  ==> 6 
public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	private IntWritable val=new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum=0;
		// [1,1,1,1,1]
		for(IntWritable i:values)
		{
			sum+=i.get();
		}
		val.set(sum); // 다시 IntWirtable로 변환
		context.write(key, val);
	}
}
