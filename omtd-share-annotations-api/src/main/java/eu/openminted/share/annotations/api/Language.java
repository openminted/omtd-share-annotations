/*
 * Licensed to the OpenMinTeD Consortium under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The OpenMinTeD Consortium 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.openminted.share.annotations.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mapping of language from OMTD-SHARE schema http://www.meta-share.org/OMTD-SHARE_XMLSchema/v201/OMTD-SHARE-ResourceCommon.xsd
 * <p>
 * The language that is used in the resource or supported by the component, as specified in the BCP47 guidelines
 * (https://tools.ietf.org/html/bcp47); the language tag is built upon (a) language tag according to ISO 639-1 and for
 * languages not covered by this, the ISO 639-3; (b) the script tag according to ISO 15924; (c) the region tag
 * according to ISO 3166-1; (d) the variant subtag
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Language
{
    @Deprecated String languageTag() default "";
    String languageId() default "";
    @Deprecated String scriptId() default "";
    @Deprecated String regiontId() default "";
    @Deprecated String variantId() default "";
}
