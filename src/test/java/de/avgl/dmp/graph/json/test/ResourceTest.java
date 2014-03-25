package de.avgl.dmp.graph.json.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import de.avgl.dmp.graph.json.Node;
import de.avgl.dmp.graph.json.Predicate;
import de.avgl.dmp.graph.json.Resource;
import de.avgl.dmp.graph.json.ResourceNode;
import de.avgl.dmp.graph.json.Statement;
import de.avgl.dmp.graph.json.test.util.TestUtil;
import de.avgl.dmp.graph.json.util.Util;

public class ResourceTest {

	@Test
	public void testSerializeResource() throws IOException {

		final ResourceNode subject = new ResourceNode(1, "http://data.slub-dresden.de/datamodels/22/records/18d68601-0623-42b4-ad89-f8954cc25912");
		final Predicate predicate = new Predicate("http://www.openarchives.org/OAI/2.0/header");
		final Node object = new Node(2);

		final Statement statement = new Statement(1, subject, predicate, object);
		final Resource resource = new Resource("http://data.slub-dresden.de/datamodels/22/records/18d68601-0623-42b4-ad89-f8954cc25912");
		resource.addStatement(statement);
		final String resourceJSONString = Util.getJSONObjectMapper().writeValueAsString(resource);

		final String expectedJSONString = TestUtil.getResourceAsString("resource.json");

		Assert.assertEquals("wrong serialisation", expectedJSONString, resourceJSONString);
	}

	@Test
	public void testDeserializeResource() throws IOException {

		final String resourceJSONString = TestUtil.getResourceAsString("resource.json");
		final Resource resource = Util.getJSONObjectMapper().readValue(resourceJSONString, Resource.class);

		Assert.assertNotNull("deserialized resource shouldn't be null", resource);

		final ResourceNode expectedSubject = new ResourceNode(1,
				"http://data.slub-dresden.de/datamodels/22/records/18d68601-0623-42b4-ad89-f8954cc25912");
		final Predicate expectedPredicate = new Predicate("http://www.openarchives.org/OAI/2.0/header");
		final Node expectedObject = new Node(2);

		final Statement expectedStatement = new Statement(1, expectedSubject, expectedPredicate, expectedObject);

		final Resource expectedResource = new Resource("http://data.slub-dresden.de/datamodels/22/records/18d68601-0623-42b4-ad89-f8954cc25912");
		expectedResource.addStatement(expectedStatement);

		Assert.assertEquals("uris of the resource should be equal", expectedResource.getUri(), resource.getUri());
		Assert.assertNotNull("there should be some statements for the resource", resource.getStatements());
		Assert.assertEquals("statement size of the resource should be equal", expectedResource.getStatements().size(), resource.getStatements()
				.size());
		Assert.assertEquals("ids of the statements should be equal", expectedStatement.getId(), resource.getStatements().iterator().next().getId());
		Assert.assertNotNull("subject of the statement shouldn't be null", resource.getStatements().iterator().next().getSubject());
		Assert.assertEquals("ids of the statements' subjects should be equal", expectedStatement.getSubject().getId(), resource.getStatements()
				.iterator().next().getSubject().getId());
		Assert.assertEquals("types of the statements' subjects should be equal", expectedStatement.getSubject().getType(), resource.getStatements()
				.iterator().next().getSubject().getType());
		Assert.assertNotNull("predicate of the statement shouldn't be null", resource.getStatements().iterator().next().getPredicate());
		Assert.assertEquals("ids of the statements' predicates should be equal", expectedStatement.getPredicate().getUri(), resource.getStatements()
				.iterator().next().getPredicate().getUri());
		Assert.assertNotNull("object of the statement shouldn't be null", resource.getStatements().iterator().next().getObject());
		Assert.assertEquals("ids of the statements' objects should be equal", expectedStatement.getObject().getId(), resource.getStatements()
				.iterator().next().getObject().getId());
		Assert.assertEquals("types of the statements' objects should be equal", expectedStatement.getObject().getType(), resource.getStatements()
				.iterator().next().getObject().getType());
		Assert.assertEquals("uris of the statements' subjects should be equal", ((ResourceNode) expectedStatement.getSubject()).getUri(),
				((ResourceNode) resource.getStatements().iterator().next().getSubject()).getUri());
	}
}