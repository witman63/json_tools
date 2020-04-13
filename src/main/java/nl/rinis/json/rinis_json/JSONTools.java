package nl.rinis.json.rinis_json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.camel.Headers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JSONTools {

	
	/** Class logger. */
	private static final Logger LOG = LoggerFactory.getLogger(JSONTools.class.getName());

	/**
	 * Private constructor to prevent instantiation.
	 */
	public JSONTools() {
	}
	
    public void jsonToHeaders(String body, @Headers Map<String, String> headers) throws ParseException {
    	
    	LOG.info("Starting JSON conversion...");
    	LOG.debug("Body input, content: {} ", body);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(body);
        if (jsonObject != null) 
        { 
        	String stringValue = null;
            String stringKey = null;
            final String NA_STRING = "*** N/A ***";
        	
	        for (Object key : jsonObject.keySet()) {

	        	stringKey = ((key == null) ? NA_STRING : (String)key);
	        	stringValue = ((jsonObject.get(stringKey) == null) ? NA_STRING : jsonObject.get(stringKey).toString());
	            headers.put(stringKey, stringValue);
		        LOG.debug("Processing key {} with value {}", stringKey, stringValue);
	        }
	        LOG.info("Done processing JSON: {}", headers.toString());
        }
    }

}