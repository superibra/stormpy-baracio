package io.barac;

import java.util.Map;

import org.apache.storm.task.ShellBolt;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;

public class PythonPredict extends ShellBolt implements IRichBolt {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PythonPredict(){
		super("/home/mab/anaconda/envs/env1/bin/python2.7","predict.py");
	}
 
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("phone","shouldcall"));	
		}
 
	public Map <String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
}