package astreaInfinitum.api.runes;

import java.util.ArrayList;
import java.util.List;

import fluxedCore.util.CoordinatePair;

public class NodeGroup {

	public List<CoordinatePair> nodes = new ArrayList<CoordinatePair>();

	public NodeGroup(CoordinatePair... pair) {
		for (CoordinatePair p : pair)
		{
			nodes.add(p);
		}
	}

	public List<CoordinatePair> getNodes() {
		return nodes;
	}

	public void setNodes(List<CoordinatePair> nodes) {
		this.nodes = nodes;
	}

}
