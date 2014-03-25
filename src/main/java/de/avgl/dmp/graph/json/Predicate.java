package de.avgl.dmp.graph.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.avgl.dmp.graph.json.deserializer.PredicateDeserializer;
import de.avgl.dmp.graph.json.serializer.PredicateSerializer;

/**
 * 
 * @author tgaengler
 *
 */
@JsonDeserialize(using = PredicateDeserializer.class)
@JsonSerialize(using = PredicateSerializer.class)
public class Predicate {

	private String uri = null;
	
	public Predicate(final String uriArg) {
		
		uri = uriArg;
	}

	
	public String getUri() {
		
		return uri;
	}

	
	public void setUri(final String uriArg) {
		
		uri = uriArg;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Predicate other = (Predicate) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
}
