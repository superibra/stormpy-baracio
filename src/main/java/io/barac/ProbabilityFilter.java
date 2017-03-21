package io.barac;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class ProbabilityFilter extends BaseBasicBolt {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double treshold;
 
	public ProbabilityFilter(Double treshold) {
		this.treshold = treshold;
	}
 
	public void execute(Tuple input, BasicOutputCollector collector) {
		if (Double.parseDouble((String) input.getValue(1))>treshold){
			collector.emit(input.getValues());
		}
	}
 
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("phone","shouldcall"));	
	}
 
}