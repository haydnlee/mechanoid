package com.example.books.net;

import com.robotoworks.mechanoid.net.Transformer;
import com.robotoworks.mechanoid.net.TransformException;
import com.robotoworks.mechanoid.internal.util.JsonReader;
import com.robotoworks.mechanoid.internal.util.JsonUtil;
import com.robotoworks.mechanoid.internal.util.JsonToken;

public class BookInputTransformer extends Transformer<JsonReader, Book> {
	public Book transform(JsonReader source) throws TransformException {
		Book target = new Book();

		transform(source, target);
		
		return target;
	}
	
	public void transform(JsonReader source, Book target) throws TransformException {
		try {
			source.beginObject();
			
			while(source.hasNext()) {
				String name = source.nextName();
				
				if(name.equals("id")) {
					target.setId(source.nextInt());
				}
				else if(name.equals("title")) {
					target.setTitle(source.nextString());
				}
				else if(name.equals("author")) {
					target.setAuthor(source.nextString());
				}
			}
			
			source.endObject();
			
		} catch (Exception x) {
			throw new TransformException(x);
		}
		
	}
}
