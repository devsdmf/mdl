package io.devsdmf.mdl.cli.provider.extractor;

import io.devsdmf.mdl.provider.Extractor;
import java.util.Map;

public interface Builder {

    Extractor build(Map<String,Object> options) throws BuilderException;
}