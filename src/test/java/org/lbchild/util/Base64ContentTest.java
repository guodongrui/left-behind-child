package org.lbchild.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lbchild.util.Base64Content;

public class Base64ContentTest {

    @Test
    public void decodetest() {
        String encodedContent = "PABoAHQAbQBsAD4APABiAG8AZAB5AD4APABQAD4AV1P9kK+LsIsFgMSeYE4fT1l1iFs/UeV6L2bRj3ReZWcATipOCGE+ZoF6+lGEdu6VmJga/9ZO7E6EdjZyzWs6ToZOH3WhixZZ+lFTYuVdDP9GT1xPOk5QW3NZhHbWTuxOdFNZdShXhk6cUVFntlvMkQz/1k7sToR2EGJ/lQ5OWWWygO6VmJgVX9FTf17bbMVi518M/0ZPiF8RXAlnuk5VXABfB1KeW0yIqFICMABO9Hb9j0JsAU8aTg5OPnkaT4xUEIzRU1VchHadW2yafWxmjwz/Y2soV55bvWUATnmYc1HobFl1iFs/UeV6hHYcIKZg+4uhixJSHSAM/xqQx4/Efsd+z34AlUZVjFQxcsNfZo87TlBjYI3+VmZODP/Vi/5Wnlg6Xz55Gk/5W1l1iFs/UeV6hHZzUehsjFR1VKRiDP/zgcpO8l3fUsaW/lZmTjEANAA0ADAAMACMUQIwDQAKADwAUAA+ADEAMgAIZzIANADlZS0AMgA2AOVlDP9CAE0AVwAcIOtfUE4FlvuLaFQrZx0gO22oUmVnMFKKf85Xf17eXQz/GpDHj1BjYI0xcsNf/lZmTiAAATBaU2lyhpnCU8KJSXswTsxbGllpX4R2O22oUoVRuVsM/41Rpl5HbFqABVPsYs9+AJVGVQEwMXLDX2aPO05JeyhXhVGEdp1bbJoxcsNfJ1m2W61ehHabUs+RDP86Tn9e3l0CXsSe1Fc6UwxUwU5mWyFohHYxADgADVRZdYhbP1HleiZeu1NzUTFyjFR1VKRiDP8uXqlSaVtQW+xOKFfTW1llvGVQToR2uWUPXwtO73mBZ3Js1lPld8aLDP+rjsNfZVC3XjBX619QThBif5UCMA0ACgA8AFAAPgAKTmhUK2edW2yaHCDrX1BOBZb7i2hUK2cdIAxU9mUoVylZJW0BMApOd22MVH+JiVszACdZzlcCXgpOFG8CMABOLGcsZ39ifY9AdzFyw1+Edv5WZk4M/yhX2Y8qTqxRKVkM/zpO0Y9+dg1UWXWIWz9R5XomXrtThk7gZVCWhHZzUTFyAjBkloZO/lZmTiAAUGNgjQz/aVtQW+xO2I8oVzFyw19mjztOylMMVISfD1wZTzRPhHZqljRPC04M/0V1OG5aU2lyhpkBMJJjFG8cIP6LLGdnUh0gATAoVyJr8FgRe+2L9JUATgxUImumXiNX3ouCggz/Ok7CUw5OdlEtToR2z2sATipOuk79kFl1C06GTo5/fVneVsZfAjANAAoAPABQAD4APAAvAFAAPgA8AC8AYgBvAGQAeQA+ADwALwBoAHQAbQBsAD4A";
        String decodedContent;
        try {
            decodedContent = Base64Content.decode(encodedContent);
            assertEquals(decodedContent,
                    "<html><body><P>南都讯记者黄习伟留守儿童是近年来一个愈显突出的问题：他们的父母为了生计外出打工，但作为子女的他们却留在了农村家里，他们的成长与教育问题引发广泛担忧，但很少有人展开切实行动。一直追求企业与社会和谐发展的宝马汽车，正在实施一项关注留守儿童的“悦读计划”，通过组织经销商和爱心车主捐赠图书，试图增强社会对留守儿童的关注和呵护，至今已募集图书14400册。\r\n"
                            + "<P>12月24日-26日，BMW“快乐阅读周末”活动来到羊城广州，通过捐赠爱心图书 、博物馆参观等丰富多彩的活动内容，再度汇聚包括经销商、爱心车主等在内的宝马爱心大家庭的力量，为广州市黄埔区同仁学校的18名留守儿童带去关爱和呵护，帮助孩子们在寓教於乐的方式下积极汲取知识，身心健康地快乐成长。\r\n"
                            + "<P>上周末宝马“快乐阅读周末”同时在天津、上海和西安3大城市上演。一本本承载着爱心的图书，在这个冬天，为近百名留守儿童带去了无限的关爱。除了图书 捐赠，孩子们还在爱心车主及同龄小伙伴的陪伴下，畅游博物馆、排演“课本剧”、在欢声笑语间一同欢度圣诞节，为参与其中的每一个人都留下了美好回忆。\r\n"
                            + "<P></P></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void encodetest() {
        String decodedContent = "<html><body><P>南都讯记者黄习伟留守儿童是近年来一个愈显突出的问题：他们的父母为了生计外出打工，但作为子女的他们却留在了农村家里，他们的成长与教育问题引发广泛担忧，但很少有人展开切实行动。一直追求企业与社会和谐发展的宝马汽车，正在实施一项关注留守儿童的“悦读计划”，通过组织经销商和爱心车主捐赠图书，试图增强社会对留守儿童的关注和呵护，至今已募集图书14400册。\r\n"
                + "<P>12月24日-26日，BMW“快乐阅读周末”活动来到羊城广州，通过捐赠爱心图书 、博物馆参观等丰富多彩的活动内容，再度汇聚包括经销商、爱心车主等在内的宝马爱心大家庭的力量，为广州市黄埔区同仁学校的18名留守儿童带去关爱和呵护，帮助孩子们在寓教於乐的方式下积极汲取知识，身心健康地快乐成长。\r\n"
                + "<P>上周末宝马“快乐阅读周末”同时在天津、上海和西安3大城市上演。一本本承载着爱心的图书，在这个冬天，为近百名留守儿童带去了无限的关爱。除了图书 捐赠，孩子们还在爱心车主及同龄小伙伴的陪伴下，畅游博物馆、排演“课本剧”、在欢声笑语间一同欢度圣诞节，为参与其中的每一个人都留下了美好回忆。\r\n"
                + "<P></P></body></html>";
        String encodedContent;
        try {
            encodedContent = Base64Content.encode(decodedContent);
            assertEquals(encodedContent,
                    "PABoAHQAbQBsAD4APABiAG8AZAB5AD4APABQAD4AV1P9kK+LsIsFgMSeYE4fT1l1iFs/UeV6L2bRj3ReZWcATipOCGE+ZoF6+lGEdu6VmJga/9ZO7E6EdjZyzWs6ToZOH3WhixZZ+lFTYuVdDP9GT1xPOk5QW3NZhHbWTuxOdFNZdShXhk6cUVFntlvMkQz/1k7sToR2EGJ/lQ5OWWWygO6VmJgVX9FTf17bbMVi518M/0ZPiF8RXAlnuk5VXABfB1KeW0yIqFICMABO9Hb9j0JsAU8aTg5OPnkaT4xUEIzRU1VchHadW2yafWxmjwz/Y2soV55bvWUATnmYc1HobFl1iFs/UeV6hHYcIKZg+4uhixJSHSAM/xqQx4/Efsd+z34AlUZVjFQxcsNfZo87TlBjYI3+VmZODP/Vi/5Wnlg6Xz55Gk/5W1l1iFs/UeV6hHZzUehsjFR1VKRiDP/zgcpO8l3fUsaW/lZmTjEANAA0ADAAMACMUQIwDQAKADwAUAA+ADEAMgAIZzIANADlZS0AMgA2AOVlDP9CAE0AVwAcIOtfUE4FlvuLaFQrZx0gO22oUmVnMFKKf85Xf17eXQz/GpDHj1BjYI0xcsNf/lZmTiAAATBaU2lyhpnCU8KJSXswTsxbGllpX4R2O22oUoVRuVsM/41Rpl5HbFqABVPsYs9+AJVGVQEwMXLDX2aPO05JeyhXhVGEdp1bbJoxcsNfJ1m2W61ehHabUs+RDP86Tn9e3l0CXsSe1Fc6UwxUwU5mWyFohHYxADgADVRZdYhbP1HleiZeu1NzUTFyjFR1VKRiDP8uXqlSaVtQW+xOKFfTW1llvGVQToR2uWUPXwtO73mBZ3Js1lPld8aLDP+rjsNfZVC3XjBX619QThBif5UCMA0ACgA8AFAAPgAKTmhUK2edW2yaHCDrX1BOBZb7i2hUK2cdIAxU9mUoVylZJW0BMApOd22MVH+JiVszACdZzlcCXgpOFG8CMABOLGcsZ39ifY9AdzFyw1+Edv5WZk4M/yhX2Y8qTqxRKVkM/zpO0Y9+dg1UWXWIWz9R5XomXrtThk7gZVCWhHZzUTFyAjBkloZO/lZmTiAAUGNgjQz/aVtQW+xO2I8oVzFyw19mjztOylMMVISfD1wZTzRPhHZqljRPC04M/0V1OG5aU2lyhpkBMJJjFG8cIP6LLGdnUh0gATAoVyJr8FgRe+2L9JUATgxUImumXiNX3ouCggz/Ok7CUw5OdlEtToR2z2sATipOuk79kFl1C06GTo5/fVneVsZfAjANAAoAPABQAD4APAAvAFAAPgA8AC8AYgBvAGQAeQA+ADwALwBoAHQAbQBsAD4A");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
