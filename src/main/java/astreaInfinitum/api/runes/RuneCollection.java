package astreaInfinitum.api.runes;

import java.util.ArrayList;
import java.util.List;

public class RuneCollection {

	private List<NodeGroup> runes = new ArrayList<NodeGroup>();

	public RuneCollection(NodeGroup... nodes) {
		for (NodeGroup n : nodes) {
			runes.add(n);
		}
	}

	public List<NodeGroup> getRunes() {
		return runes;
	}

	public void setRunes(List<NodeGroup> runes) {
		this.runes = runes;
	}

}
