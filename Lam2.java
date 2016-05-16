package la;

import static org.junit.Assert.assertArrayEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class Lam2 {

    @Test
    public void test() throws Exception {

        Object[] INFO = {
                new PropName<String>("col1"), A,
                new PropName<String>("col2"), B,
                new PropName<String>("col3"), C,
                new PropName<Integer>("col4"), D,
                new PropName<Date>("col5"), E,
                new PropName<Long>("col6"), F
        };

        Map<String, String> param = new HashMap<>();
        param.put("col1", "aaaaa");
        param.put("col3", "cccc");
        param.put("col4", "4444");
        param.put("col5", "yyyy/mm/dd hh:mi:ss.SSS");

        String[] inserParams = IntStream.range(0, INFO.length - 1)
                .filter(idx -> idx % 2 == 0)
                .mapToObj(idx -> {
                    String paramKey = String.valueOf(INFO[idx]);
                    if (param.containsKey(paramKey)) {
                        return param.get(paramKey);
                    } else {
                        switch (String.valueOf(INFO[idx + 1])) {
                        case A:
                            return V_A;
                        case B:
                            return V_b;
                        case C:
                            return V_C;
                        case D:
                            return V_D;
                        case E:
                            return V_E;
                        case F:
                        default:
                            return V_F;
                        }
                    }
                })
                .collect(Collectors.toList())
                .toArray(new String[0]);

        assertArrayEquals(new String[] {
                "aaaaa",
                V_b,
                "cccc",
                "4444",
                "yyyy/mm/dd hh:mi:ss.SSS",
                V_F
        }, inserParams);
    }

    static final String A = "a";
    static final String B = "b";
    static final String C = "c";
    static final String D = "d";
    static final String E = "e";
    static final String F = "f";

    static final String V_A = "v_a";
    static final String V_b = "v_b";
    static final String V_C = "v_c";
    static final String V_D = "v_d";
    static final String V_E = "v_e";
    static final String V_F = "v_f";
}

class PropName<T> {

    public final String name;

    PropName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
