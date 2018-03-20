
package finalprojectB;

import finalprojectB.UrlValidator;
import junit.framework.TestCase;
import java.util.*;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {
  
  
  public UrlValidatorTest(String testName) {
    super(testName);
  }
  
  
  
  public void testManualTest()
  {
    UrlValidator urlVal = new UrlValidator(null, null, 1);
    
    String[] validUrls = {
      "http://www.google.com",
      "http://www.google.com:8080",
      "http://www.tech.yahoo.com/rc/desktops/102;_ylt=Ao8yevQHlZ4On0O3ZJGXLEQFLZA5",
      "http://foo.com/blah_blah",
      "http://foo.com/blah_blah/",
      "http://foo.com/blah_blah_(wikipedia)",
      "http://foo.com/blah_blah_(wikipedia)_(again)",
      "http://www.example.com/wpstyle/?p=364",
      "https://www.example.com/foo/?bar=baz&inga=42&quux",
      "http://userid:password@example.com:8080",
      "http://userid:password@example.com:8080/",
      "http://userid@example.com",
      "ftp://foo.bar/baz"
    };
    
    String[] invalidUrls = {
      "http://",
      "http://.",
      "http://..",
      "http://../",
      "http://?",
      "http://??",
      "http://??/",
      "http://#",
      "http://##",
      "http://##/",
      "ftps://foo.bar/",
      "http://123.123.123"
    };
    
    for (int i = 0; i < invalidUrls.length; i++) {
      assertFalse(new String(invalidUrls[i] + " Should NOT passs as a valid URL.  "), urlVal.isValid(invalidUrls[i]));
    }
    
    for(int i = 0; i < validUrls.length; i++) {
      assertTrue(new String(validUrls[i] + " Should passs as a valid URL.  "), urlVal.isValid(validUrls[i]));
    }	    
  }
  
  
  public void testYourFirstPartition()
  {
    UrlValidator urlVal = new UrlValidator(null, null, 1);
    assertFalse("The empty URL should not be valid", urlVal.isValid(""));
    assertFalse("A single space character should not be valid", urlVal.isValid(" "));
    assertFalse("Null URLS shouldn't be accepted", urlVal.isValid(null));
    assertFalse("A URL without a scheme, port, or authority shouldn't be accepted", urlVal.isValid(".com"));
    assertFalse("A url without a domain extension shouldn't be allowed", urlVal.isValid("http://google"));
    assertFalse("Malformed URLS shouldn't be accepted", urlVal.isValid("://google"));
    assertFalse("URLS without schemes or authorities should not be accepted.", urlVal.isValid("google"));
  }
  
  public void testYourSecondPartition() {
    UrlValidator urlVal = new UrlValidator(null, null, 1);
    assertFalse("Domains with extraneous schemes shouldn't pass", urlVal.isValid("http://http://google.com"));
    assertFalse("Domains with extraneous extensions shouldn't pass", urlVal.isValid("http://google.com.net.edu"));
    
    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    StringBuilder sb = new StringBuilder(5000);
    Random random = new Random();
    for (int i = 0; i < 5000; i++) {
      char c = chars[random.nextInt(chars.length)];
      sb.append(c);
    }
    
    String tooLongUrl = "http://www." + sb.toString() + ".com";
    assertFalse("Domains with more than 2083 characters should fail", urlVal.isValid(tooLongUrl));
  }
  
  public void testIsValid()
  {
    UrlValidator urlVal = new UrlValidator(null, null, 1);
    // Combine each valid scheme with each valid authority, they should all fail
    for(int i = 0; i < validUrlSchemes.length; i++) {    
      // The same url should be valid with all combinations of valid ports tested.
      for (int j = 0; j < validUrlAuthority.length; j++) {
        String url = validUrlSchemes[i] + validUrlAuthority[j];
        assertTrue(
        url + " Should be valid valid scheme paired with a valid authority and valid authority should be a valid URL.",
        urlVal.isValid(url));
        
        // The same url should be valid with all combinations of valid ports tested.
        for (int k = 0; k < validUrlPorts.length; k++) {
          String urlWithPort = validUrlSchemes[i] + validUrlAuthority[i] + validUrlPorts[k];
          assertTrue(
          urlWithPort + " Should be valid valid scheme paired with a valid authority and valid port should be a valid URL.",
          urlVal.isValid(urlWithPort));
          
          // The same url should be valid with all combinations of valid paths tested.
          for (int l = 0; l < validUrlPaths.length; l++) {
            String urlWithPath = validUrlSchemes[i] + validUrlAuthority[i] + validUrlPorts[k] + validUrlPaths[l];
            assertTrue(urlWithPath
            + " Should be valid valid scheme paired with a valid authority, port, and valid path should be a valid URL.",
            urlVal.isValid(urlWithPath));
          }  
        }
      } 
    }    
  }
  
  
  String[] validUrlSchemes = {"http://", "https://", "h3t://", ""};
  String[] invalidUrlSchemes = {"3ht://", "http:/", "http:", "http/", "://"};
  
  String[] validUrlAuthority = {"www.google.com", "go.com", "go.au", "0.0.0.0", "255.255.255.255", "255.com"};
  String[] invalidUrlAuthority = {"256.256.256.256", "1.2.3.4.5", "1.2.3.4", "1.2.3", ".1.2.3.4", "go.a", "go.a1a", "go.1aa", "aaa.", ".aaa", "aaa", ""};
  
  String[] validUrlPorts = {":80", ":65535", ":0", ""};
  String[] invalidUrlPorts = {":-1", ":65636", ":65a"};
  
  
  String[] validUrlPaths = {"/test1", "/t123", "/$23", "/test1/", "", "/test1/file"};
  String[] invalidUrlPaths = {"/..", "/../", "/..//file", "/test1//file"};
  
  
  String[] validUrlPathOptions  = {"/test1", "t123", "/$23", "/test1/", "", "/test1/file", "/$23/file", "/test1//file"};
  String[] invalidUrlPathOptions = {"/..", "/../", "/#", "/../file", "/..//file", "/#/file"};
  
  
}
