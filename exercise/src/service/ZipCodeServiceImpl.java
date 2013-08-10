package service;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@WebService(endpointInterface = "service.ZipCodeService", serviceName = "ZipCodeService")
public class ZipCodeServiceImpl implements ZipCodeService {
    private static final Map<Integer, State>  STATES;

    static {
        STATES = new HashMap<Integer, State>();
        STATES.put(84111, new State("Utah", "UT"));
    }

    @Override
    public State getState(Integer zipCode) {
        return STATES.get(zipCode);
    }

    @Override
    public void addZipCode(State state, List<Integer> zipCodes) {
       for(Integer zipCode : zipCodes) {
        if(!STATES.containsKey(zipCode)) {
         STATES.put(zipCode, state);
        }
       }
    }
}
