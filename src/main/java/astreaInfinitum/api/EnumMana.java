package astreaInfinitum.api;

public enum EnumMana {
	light {
		public String getNBTName() {
			return "AIPlayerManaLight";
		}
	},
	dark {
		public String getNBTName() {
			return "AIPlayerManaDark";
		}
	};

	public String getNBTName() {
		return "";
	}
}
