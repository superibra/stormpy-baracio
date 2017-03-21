package io.barac;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

public class Topology {
	 
	public static void main(String[] args) throws Exception {
	    TopologyBuilder builder = new TopologyBuilder();
 
	    builder.setSpout("logs", new LogsSpout(), 1);
	    builder.setBolt("predictions", new PythonPredict(), 8).shuffleGrouping("logs");
	    builder.setBolt("filter", new ProbabilityFilter(0.3), 4).shuffleGrouping("predictions");
	    builder.setBolt("dump", new DumpToRedis(), 4).shuffleGrouping("filter");
 
	    Config conf = new Config();
	    conf.setDebug(true);
 
	    if (args != null && args.length > 0) {
	      conf.setNumWorkers(3);
 
	      StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
	    }
	    else {
	      LocalCluster cluster = new LocalCluster();
	      cluster.submitTopology("test", conf, builder.createTopology());
	      Utils.sleep(100000);
	      cluster.killTopology("test");
	      cluster.shutdown();
	    }
	  }
}