package org.pipservices.commons.data;

import java.util.*;

public class ProjectionParams extends ArrayList<String> { //implements Cloneable 
	
	private static final long serialVersionUID = 5876557837753631885L;
	
	public ProjectionParams(){ }
	
    public ProjectionParams(String[] values){
        if (values != null)
        {
        	for( int i = 0; i < values.length; i++ )
            add(values[i]);
        }
    }
    
    public ProjectionParams(AnyValueArray array){
        if (array == null)
        {
            return;
        }

        for (int index = 0; index < array.size(); index++)
        {
            String value = array.getAsString(index);
            if (!isNullOrWhiteSpace(value))
            {
                add(value);
            }
        }
    }
    
    public static ProjectionParams fromValue(Object value)
    {
        if ( value instanceof ProjectionParams) //// value.getClass() == ProjectionParams.class
        {
            return (ProjectionParams)value;
        }

        AnyValueArray array = value != null ? AnyValueArray.fromValue(value) : new AnyValueArray();
        return new ProjectionParams(array);
    }
    
    public static ProjectionParams fromValues( String... values)
    {
        return new ProjectionParams( parse(values).toArray(new String[values.length]) );
    }
    
    public String toString()
    {
    	StringBuilder builder = new StringBuilder();
        for (int index = 0; index < builder.length(); index++)
        {
            if (index > 0)
            {
                builder.append(',');
            }

            builder.append(super.get(index));
        }
        return builder.toString();
    }
    
    private static ProjectionParams parse(String[] values)
    {
    	ProjectionParams result = new ProjectionParams();
        String prefix = "";

        for (String value : values)
        {
            parseValue(prefix, result, value.trim());
        }

        return result;
    }
    
    
    private static void parseValue(String prefix, ProjectionParams result, String value)
    {
        if (!isNullOrWhiteSpace(value))
        {
            value = value.trim();
        }

        int openBracket = 0;
        int openBracketIndex = -1;
        int closeBracketIndex = -1;
        int commaIndex = -1;

        boolean breakCycleRequired = false;
        
        for (int index = 0; index < value.length(); index++)
        {
            switch (value.charAt(index))
            {
                case '(':
                    if (openBracket == 0)
                    {
                        openBracketIndex = index;
                    }

                    openBracket++;
                    break;
                case ')':
                    openBracket--;

                    if (openBracket == 0)
                    {
                        closeBracketIndex = index;

                        if (openBracketIndex >= 0 && closeBracketIndex > 0)
                        {
                            String previousPrefix = prefix;

                            if (!isNullOrWhiteSpace(prefix))
                            {
                                prefix = "{prefix}.{value.substring(0, openBracketIndex)}";
                            }
                            else
                            {
                                prefix = "{value.substring(0, openBracketIndex)}";
                            }

                            String subValue = value.substring(openBracketIndex + 1, closeBracketIndex);
                            parseValue(prefix, result, subValue);

                            subValue = value.substring(closeBracketIndex + 1);
                            parseValue(previousPrefix, result, subValue);
                            breakCycleRequired = true;
                        }
                    }
                    break;
                case ',':
                    if (openBracket == 0)
                    {
                        commaIndex = index;

                        String subValue = value.substring(0, commaIndex);

                        if (!isNullOrWhiteSpace(subValue))
                        {
                            if (!isNullOrWhiteSpace(prefix))
                            {
                                result.add("{prefix}.{subValue}");
                            }
                            else
                            {
                                result.add(subValue);
                            }
                        }

                        subValue = value.substring(commaIndex + 1);

                        if (!isNullOrWhiteSpace(subValue))
                        {
                            parseValue(prefix, result, subValue);
                            breakCycleRequired = true;
                        }
                    }
                    break;
            }

            if (breakCycleRequired)
            {
                break;
            }
        }

        if (!isNullOrWhiteSpace(value) && openBracketIndex == -1 && commaIndex == -1)
        {
            if (!isNullOrWhiteSpace(prefix))
            {
                result.add("{prefix}.{value}");
            }
            else
            {
                result.add(value);
            }
        }
    }
     
    
    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || isWhitespace(s);
    }
    
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
}
