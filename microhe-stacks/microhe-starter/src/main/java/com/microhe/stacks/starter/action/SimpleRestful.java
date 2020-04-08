package com.microhe.stacks.starter.action;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.microee.plugin.response.R;

@RestController
@RequestMapping("/simple")
public class SimpleRestful {

    /**
     * get R
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public R<?> get(@RequestParam("id") String id) {
        return R.ok(id);
    }

    /**
     * post C
     *
     * @param id
     * @param body
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public R<?> create(@RequestParam("id") String id, @RequestBody String body) {
        return R.ok(body);
    }

    //  curl -v -X POST -H "Content-Type: application/json" \
    //       --data '/home/vagrant/Desktop/VirtualBox-6.0.14-133895-OSX.dmg' \
    //       http://172.28.128.4:10004/simple/RequestBody 
    /**
     * base64 编码
     *
     * @param text
     * @return base64 结果被 url encoder
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/base64encoder", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public String base64Encoder(@RequestBody String text) throws UnsupportedEncodingException {
        String base64DecoderString = new String(new Base64().encode(text.getBytes(StandardCharsets.UTF_8.toString())));
        return java.net.URLEncoder.encode(base64DecoderString, "UTF-8");
    }

    /**
     * base64 解码
     *
     * @param text
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/base64Decoder", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE)
    public String base64Decoder(@RequestParam String text) throws UnsupportedEncodingException {
        String base64DecoderString = new String(new Base64().decode(text.getBytes(StandardCharsets.UTF_8.toString())));
        return base64DecoderString;
    }
}
