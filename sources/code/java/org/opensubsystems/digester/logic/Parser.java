/*
 * Copyright (C) 2003 - 2013 OpenSubsystems.com/net/org and its owners. All rights reserved.
 * 
 * This file is part of OpenSubsystems.
 *
 * OpenSubsystems is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.opensubsystems.digester.logic;

import java.util.Map;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.digester.data.Record;
import org.opensubsystems.digester.data.DigesterContext;

/**
 * Parser capable of parsing unstructured data to structured data and then 
 * creating Java objects from the parse data.
 * 
 * @author bastafidli
 */
public interface Parser<C extends DigesterContext, R extends Record, O> 
{
   /**
    * Parse the record into map of attributes and values.
    * 
    * @param context - context within which the record is processed
    * @param record - record to parse
    * @return Map<String, Object> - record parsed into attributes and values. The 
    *                               key is the attribute name or some other 
    *                               identifier and the value is the actual 
    *                               attribute value parsed from the record
    * @throws OSSException - an error has occurred
    */
   Map<String, Object> parse(
      C context,
      R record
   ) throws OSSException;
   
   /**
    * Create the object based on the previously parsed values.
    * 
    * @param context - context within which the record is processed
    * @param values - values produced by the parse operation
    * @return O - object created from the parsed values
    * @throws OSSException - an error has occurred
    */
   O create(
      C                   context,
      Map<String, Object> values
   ) throws OSSException;
}
