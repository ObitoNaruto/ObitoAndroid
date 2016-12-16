package android.mobile.widget.ui.BannerView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.L;

/**
 * 定制图片来源过滤器
 */
public class Md5FileNameGenerator implements FileNameGenerator {
    private static final String HASH_ALGORITHM = "MD5";
    private static final int RADIX = 36;
    private Pattern mPattern;

    public Md5FileNameGenerator() {
        mPattern = Pattern.compile("(http://[ft]\\d+\\.market.xiaomi.com/)|(http://[ft]\\d+\\.market.mi-img.com/)");
    }

    public String generate(String imageUri) {
        String uri = imageUri;
        Matcher matcher = mPattern.matcher(imageUri);
        if (matcher.lookingAt()) {
            uri = matcher.replaceFirst("");
        }
        byte[] md5 = this.getMD5(uri.getBytes());
        BigInteger bi = (new BigInteger(md5)).abs();
        return bi.toString(RADIX);
    }

    private byte[] getMD5(byte[] data) {
        byte[] hash = null;
        try {
            MessageDigest e = MessageDigest.getInstance(HASH_ALGORITHM);
            e.update(data);
            hash = e.digest();
        } catch (NoSuchAlgorithmException var4) {
            L.e(var4);
        }
        return hash;
    }

}