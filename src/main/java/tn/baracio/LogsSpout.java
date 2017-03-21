package tn.baracio;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class LogsSpout extends BaseRichSpout {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Jedis client;
	SpoutOutputCollector _collector;
 
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
	    _collector = collector;
		client = new Jedis("");
		client.connect();
	}
 
	public void nextTuple() {
	    _collector.emit(new Values(client.lpop("logs")));
	}
 
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	    declarer.declare(new Fields("log"));
	}
}