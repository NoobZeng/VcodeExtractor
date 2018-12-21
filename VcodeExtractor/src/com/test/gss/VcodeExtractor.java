package com.test.gss;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
 
import org.apache.jmeter.processor.PostProcessor;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractScopedTestElement;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class VcodeExtractor extends AbstractScopedTestElement implements PostProcessor, Serializable{
    private static final long serialVersionUID = 1L;   
    private static final Logger log = LoggerFactory.getLogger(VcodeExtractor.class);
    
    public void process() {
 
        // TODO Auto-generated method stub
        JMeterContext context = getThreadContext();
        SampleResult previousResult = context.getPreviousResult();
        if (previousResult == null) {
            return;
        }
        log.debug("VcodeExtractor processing result");
 
        String status = previousResult.getResponseCode();
        int id = context.getThreadNum();
        //String imageName = id + ".jpg";
        String path = "E://" + id + ".jpg";
 
        if(status.equals("200")){
            byte[] buffer = previousResult.getResponseData();
            FileOutputStream out = null;
            File file = null;
            try {
                file = new File(path);
                out = new FileOutputStream(file);
                out.write(buffer);
                out.flush();
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if(out != null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
 
              try {   
                String vcode = new OCR().recognizeText(file, "jpeg"); 
                vcode = vcode.replace(" ", "").trim();
                
                JMeterVariables var = context.getVariables();
                var.put("vcode", vcode);
                var.put("vuser", String.valueOf(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }    
        }
 
    }
 
}
