/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.obd.consts;

import java.util.HashMap;
import java.util.Map;

public final class ObdComputerConsts {
	
	public static final Map<String, String> SPEC_MAP = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8223603251326962141L;

		{
			put("1", "OBD­II as defined by the CARB");
			put("2", "OBD as defined by the EPA");
			put("3", "OBD and OBD­II");
			put("4", "OBD­I");
			put("5", "Not OBD compliant");
			put("6", "EOBD (Europe)");
			put("7", "EOBD and OBD­II");
			put("8", "EOBD and OBD");
			put("9", "EOBD, OBD and OBD II");
			put("10", "JOBD (Japan)");
			put("11", "JOBD and OBD II");
			put("12", "JOBD and EOBD");
			put("13", "JOBD, EOBD, and OBD II");
			put("14", "Reserved");
			put("15", "Reserved");
			put("16", "Reserved");
			put("17", "Engine Manufacturer Diagnostics (EMD)");
			put("18", "Engine Manufacturer Diagnostics Enhanced (EMD+)");
			put("19", "Heavy Duty On­Board Diagnostics (Child/Partial) (HD OBD­C)");
			put("20", "Heavy Duty On­Board Diagnostics (HD OBD)");
			put("21", "World Wide Harmonized OBD (WWH OBD)");
			put("22", "Reserved");
			put("23", "Heavy Duty Euro OBD Stage I without NOx control (HD EOBD­I)");
			put("24", "Heavy Duty Euro OBD Stage I with NOx control (HD EOBD­I N)");
			put("25", "Heavy Duty Euro OBD Stage II without NOx control (HD EOBD­II)");
			put("26", "Heavy Duty Euro OBD Stage II with NOx control (HD EOBD­II N)");
			put("27", "Reserved");
			put("28", "Brazil OBD Phase 1 (OBDBr­1)");
			put("29", "Brazil OBD Phase 2 (OBDBr­2)");
			put("30", "Korean OBD (KOBD)");
			put("31", "India OBD I (IOBD I)");
			put("32", "India OBD II (IOBD II)");
			put("33", "Heavy Duty Euro OBD Stage VI (HD EOBD­IV)");
		}
	};

}
