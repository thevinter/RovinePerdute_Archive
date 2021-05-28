package glisprogrammatori.rovineperdute;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class GestoreXMLgenerico {
  private static final XMLInputFactory xml_input_factory = XMLInputFactory.newInstance();
  private static final XMLOutputFactory xml_output_factory = XMLOutputFactory.newInstance();
  private XMLStreamReader xml_stream_reader;
  private XMLStreamWriter xml_stream_writer;

  public GestoreXMLgenerico(String input_file_path, String output_file_path, String output_encoding)
      throws FileNotFoundException, XMLStreamException {
    xml_stream_reader = xml_input_factory.createXMLStreamReader(input_file_path, new FileInputStream(input_file_path));

    xml_stream_writer = xml_output_factory.createXMLStreamWriter(new FileOutputStream(output_file_path),
        output_encoding);
  }

  public static ArrayList<Field> ottieniAttributi(Class<?> aClass) {
    String nome_classe_corrente;
    ArrayList<Field> attributi = new ArrayList<Field>();

    for (Field attributo : aClass.getDeclaredFields())
      attributi.add(attributo);

    do {
      aClass = aClass.getSuperclass();
      nome_classe_corrente = aClass.getName();

      if (!nome_classe_corrente.equals("java.lang.Object")) {
        for (Field attributo : aClass.getDeclaredFields())
          attributi.add(attributo);
      }

    } while (!nome_classe_corrente.equals("java.lang.Object"));

    return attributi;
  }

  private String formattaNomeClasseTag(String da_formattare) {
    return Character.toUpperCase(da_formattare.charAt(0)) + da_formattare.substring(1);
  }

  private Object convertParameter(Parameter parameter, String value) {
    switch (parameter.getType().getSimpleName()) {
      case "byte":
      case "Byte":
        return Byte.parseByte(value);

      case "short":
      case "Short":
        return Short.parseShort(value);

      case "int":
      case "Integer":
        return Integer.parseInt(value);

      case "long":
      case "Long":
        return Long.parseLong(value);

      case "float":
      case "Float":
        return Float.parseFloat(value);

      case "double":
      case "Double":
        return Double.parseDouble(value);

      case "boolean":
      case "Boolean":
        return Boolean.parseBoolean(value);

      case "char":
      case "Character":
        return value.charAt(0);

      default:
        return value;
    }
  }

  public ArrayList<Object> leggiXMLgenerico(Class<?> obj_class) throws XMLStreamException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    ArrayList<Object> objects = new ArrayList<Object>();
    String class_name = obj_class.getSimpleName();
    Constructor<?>[] class_constructors = obj_class.getDeclaredConstructors();
    Constructor<?> constructor_to_use = class_constructors[0];
    Parameter[] constructor_parameter = {};
    Object[] parameter_value;

    while (xml_stream_reader.hasNext()) {
      if (xml_stream_reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
        if (class_name.equals(formattaNomeClasseTag(xml_stream_reader.getLocalName()))) {
          for (int i = 0; i < class_constructors.length; i++) {
            constructor_to_use = class_constructors[i];
            constructor_parameter = constructor_to_use.getParameters();

            if (constructor_parameter.length == xml_stream_reader.getAttributeCount())
              i = class_constructors.length;
          }

          parameter_value = new Object[constructor_parameter.length];
          for (int i = 0; i < constructor_parameter.length; i++)
            parameter_value[i] = convertParameter(constructor_parameter[i], xml_stream_reader.getAttributeValue(i));

          objects.add(constructor_to_use.newInstance(parameter_value));
        }
      }

      xml_stream_reader.next();
    }

    return objects;
  }
}
