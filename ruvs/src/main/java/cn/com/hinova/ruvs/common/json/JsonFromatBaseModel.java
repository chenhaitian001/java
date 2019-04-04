package cn.com.hinova.ruvs.common.json;

import java.io.IOException;

import cn.com.hinova.ruvs.config.bean.Clazz;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import cn.com.hinova.ruvs.common.base.SuperModel;
import cn.com.hinova.ruvs.utils.MapUtils;

public class JsonFromatBaseModel extends JsonSerializer<SuperModel> {


	@Override
	public void serialize(SuperModel model, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {

		String name=model.getName();
		if(model instanceof Clazz ){
			name=((Clazz) model).getGrade().getName()+" "+name;
		}
		arg1.writeObject(MapUtils.toMap("id",model.getId(),"name",name));
	}
}
