package astreaInfinitum.api;

public enum EnumPlayerEco {
	light {
		public String getNBTName() {
			return "AIPlayerEcoLight";
		}
	},
	dark {
		public String getNBTName() {
			return "AIPlayerEcoDark";
		}
	};

	public String getNBTName() {
		return "";
	}
}
