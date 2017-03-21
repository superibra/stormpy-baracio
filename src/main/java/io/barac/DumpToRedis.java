package io.barac;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class DumpToRedis extends BaseRichBolt {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Jedis client;
 
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		client = new Jedis("");
		client.connect();
	}
 
	public void execute(Tuple input) {
		if (input.getValues().size()!=0){
			client.zadd("calls", Double.parseDouble((String) input.getValue(1)), (String) input.getValue(0));
		}
	}
 
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}
}