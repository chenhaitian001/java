package cn.com.hinova.ruvs.common.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonFromatDate extends JsonSerializer<Date> {

	private static SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 


	@Override
	public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
		arg1.writeString(fmt.format(arg0));
		
	}

}
