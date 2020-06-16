package io.devsdmf.mdl.cli.converter;

import io.devsdmf.mdl.media.Type;
import picocli.CommandLine.ITypeConverter;

public class TypeConverter implements ITypeConverter<Type> {

    public Type convert(String value) throws Exception {
        return Type.fromValue(value.toLowerCase());
    }
}
