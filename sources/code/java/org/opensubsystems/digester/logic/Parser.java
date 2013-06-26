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
 * Interface representing parser that is capable of parsing unstructured data
 * to structured data.
 * 
 * @author bastafidli
 */
public interface Parser<C extends DigesterContext, R extends Record> 
{
   /**
    * Parse the record into map of attributes and values.
    * 
    * @param record - record to parse
    * @return Map<String, Object> - record parsed into attributes and values
    * @throws OSSException - an error has occurred
    */
   // TODO: Improve: Should C be an argument?
   Map<String, Object> parse(
     R record
   ) throws OSSException;
}
