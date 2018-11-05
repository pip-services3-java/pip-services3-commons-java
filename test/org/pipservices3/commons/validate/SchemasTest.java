package org.pipservices3.commons.validate;

import org.junit.*;
import org.pipservices3.commons.convert.JsonConverter;

import static org.junit.Assert.*;

import java.util.*;

public class SchemasTest
{
    @Test
    public void testEmptySchema() {
        ObjectSchema schema = new ObjectSchema();
        List<ValidationResult> results = schema.validate(null);
        assertEquals(0, results.size());
    }

    @Test
    public void TestRequired() {
        Schema schema = new Schema().makeRequired();
        List<ValidationResult> results = schema.validate(null);
        assertEquals(1, results.size());
    }

    @Test
    public void TestUnexpected() {
        Schema schema = new ObjectSchema();
        TestObject obj = new TestObject();
        List<ValidationResult> results = schema.validate(obj);
        assertEquals(8, results.size());
    }

    @Test
    public void TestOptionalProperties() {
        Schema schema = new ObjectSchema()
            .withOptionalProperty("intField", null)
            .withOptionalProperty("StringProperty", null)
            .withOptionalProperty("NullProperty", null)
            .withOptionalProperty("IntArrayProperty", null)
            .withOptionalProperty("StringListProperty", null)
            .withOptionalProperty("MapProperty", null)
            .withOptionalProperty("SubObjectProperty", null)
            .withOptionalProperty("SubArrayProperty", null);

        TestObject obj = new TestObject();
        List<ValidationResult> results = schema.validate(obj);
        assertEquals(0, results.size());
    }

    @Test
    public void TestRequiredProperties() {
        ObjectSchema schema = new ObjectSchema()
            .withRequiredProperty("intField", null)
            .withRequiredProperty("StringProperty", null)
            .withRequiredProperty("NullProperty", null)
            .withRequiredProperty("IntArrayProperty", null)
            .withRequiredProperty("StringListProperty", null)
            .withRequiredProperty("MapProperty", null)
            .withRequiredProperty("SubObjectProperty", null)
            .withRequiredProperty("SubArrayProperty", null);

        TestObject obj = new TestObject();
        obj.setSubArrayProperty(null);

        List<ValidationResult> results = schema.validate(obj);
        assertEquals(2, results.size());
    }

    @Test
    public void TestObjectTypes() {
        ObjectSchema schema = new ObjectSchema()
            .withRequiredProperty("intField", Integer.class)
            .withRequiredProperty("StringProperty", String.class)
            .withOptionalProperty("NullProperty", Object.class)
            .withRequiredProperty("IntArrayProperty", int[].class)
            .withRequiredProperty("StringListProperty", List.class)
            .withRequiredProperty("MapProperty", Map.class)
            .withRequiredProperty("SubObjectProperty", TestSubObject.class)
            .withRequiredProperty("SubArrayProperty", TestSubObject[].class);

        TestObject obj = new TestObject();
        List<ValidationResult> results = schema.validate(obj);
        assertEquals(0, results.size());
    }

    @Test
    public void TestStringTypes() {
        ObjectSchema schema = new ObjectSchema()
            .withRequiredProperty("intField", "Integer")
            .withRequiredProperty("StringProperty", "String")
            .withOptionalProperty("NullProperty", "Object")
            .withRequiredProperty("IntArrayProperty", "int[]")
            .withRequiredProperty("StringListProperty", "ArrayList")
            .withRequiredProperty("MapProperty", "HashMap")
            .withRequiredProperty("SubObjectProperty", "TestSubObject")
            .withRequiredProperty("SubArrayProperty", "TestSubObject[]");

        TestObject obj = new TestObject();
        List<ValidationResult> results = schema.validate(obj);
        assertEquals(0, results.size());
    }

    @Test
    public void TestSubSchema() {
        ObjectSchema subSchema = new ObjectSchema()
            .withRequiredProperty("Id", "String")
            .withRequiredProperty("FLOATFIELD", "float")
            .withOptionalProperty("nullproperty", "Object");

        ObjectSchema schema = new ObjectSchema()
            .withRequiredProperty("intField", "Integer")
            .withRequiredProperty("StringProperty", "String")
            .withOptionalProperty("NullProperty", "Object")
            .withRequiredProperty("IntArrayProperty", "int[]")
            .withRequiredProperty("StringListProperty", "ArrayList")
            .withRequiredProperty("MapProperty", "HashMap")
            .withRequiredProperty("SubObjectProperty", subSchema)
            .withRequiredProperty("SubArrayProperty", "TestSubObject[]");

        TestObject obj = new TestObject();
        List<ValidationResult> results = schema.validate(obj);
        assertEquals(0, results.size());
    }

    @Test
    public void TestArrayAndMapSchema() {
        ObjectSchema subSchema = new ObjectSchema()
            .withRequiredProperty("Id", "String")
            .withRequiredProperty("FLOATFIELD", "float")
            .withOptionalProperty("nullproperty", "Object");

        ObjectSchema schema = new ObjectSchema()
            .withRequiredProperty("intField", "Integer")
            .withRequiredProperty("StringProperty", "String")
            .withOptionalProperty("NullProperty", "Object")
            .withRequiredProperty("IntArrayProperty", new ArraySchema("Integer"))
            .withRequiredProperty("StringListProperty", new ArraySchema("String"))
            .withRequiredProperty("MapProperty", new MapSchema("String", "Integer"))
            .withRequiredProperty("SubObjectProperty", subSchema)
            .withRequiredProperty("SubArrayProperty", new ArraySchema(subSchema));

        TestObject obj = new TestObject();
        List<ValidationResult> results = schema.validate(obj);
        assertEquals(0, results.size());
    }

    @Test
    public void TestJsonSchema() throws Exception {        	
        ObjectSchema subSchema = new ObjectSchema()
            .withRequiredProperty("Id", "String")
            .withRequiredProperty("FLOATFIELD", "float")
            .withOptionalProperty("nullproperty", "Object");

        ObjectSchema schema = new ObjectSchema()
            .withRequiredProperty("intField", "Integer")
            .withRequiredProperty("StringProperty", "String")
            .withOptionalProperty("NullProperty", "Object")
            .withRequiredProperty("IntArrayProperty", new ArraySchema("Integer"))
            .withRequiredProperty("StringListProperty", new ArraySchema("String"))
            .withRequiredProperty("MapProperty", new MapSchema("String", "Integer"))
            .withRequiredProperty("SubObjectProperty", subSchema)
            .withRequiredProperty("SubArrayProperty", new ArraySchema(subSchema));

        TestObject obj = new TestObject();
        String json = JsonConverter.toJson(obj);
        Object jsonObj = JsonConverter.toMap(json);
        
        List<ValidationResult> results = schema.validate(jsonObj);
        assertEquals(0, results.size());
    }

}