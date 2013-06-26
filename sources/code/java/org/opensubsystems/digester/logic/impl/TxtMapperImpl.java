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

package org.opensubsystems.digester.logic.impl;

import java.util.HashMap;
import java.util.Map;
import org.opensubsystems.digester.data.DigesterContext;
import java.util.logging.Logger;
import org.opensubsystems.core.logic.impl.StatelessControllerImpl;
import org.opensubsystems.core.util.Log;
import org.opensubsystems.core.util.ThreeElementStruct;
import org.opensubsystems.digester.data.Record;
import org.opensubsystems.digester.logic.Mapper;

/**
 * Implementation of the mapper interface for the data in plain text format 
 * modeled after the commons digester package.
 *
 * @author bastafidli
 */
public abstract class TxtMapperImpl<C extends DigesterContext, R extends Record> extends StatelessControllerImpl
                                                                                 implements Mapper<C, R>
{
   // Attributes ///////////////////////////////////////////////////////////////
   
   /**
    * Map containing patterns that the mapper uses to map data in the plain text
    * format to POJO objects.
    */
   Map<String, ThreeElementStruct<String, String, Class>> m_mpPatters;
   
   // Cached values ////////////////////////////////////////////////////////////

   /**
    * Logger for this class
    */
   private static Logger s_logger = Log.getInstance(TxtMapperImpl.class);

   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Default constructor. 
    */
   public TxtMapperImpl()
   {
      m_mpPatters = new HashMap<>();
   }
   
   // Logic ////////////////////////////////////////////////////////////////////
 
   // Helper methods ///////////////////////////////////////////////////////////

   /**
    * Configure patter for specified set of attributes.
    * 
    * @param strPrefix - prefix that the record has to start with to match the
    *                    pattern
    * @param strDelimiter - delimiter used to separate the record starting with
    *                       the given prefix from the rest of the data
    * @param clsParser - parser use to parse the record to a a set of attributes
    */
   protected void forPrefixAndDelimiter(
      String strPrefix,
      String strDelimiter,
      // TODO: Improve: This should be limited to classes which implements Parser interface
      Class  clsParser
   )
   {
      ThreeElementStruct<String, String, Class> pattern;
      
      pattern = new ThreeElementStruct<>(strPrefix, strDelimiter, clsParser);
      
      m_mpPatters.put(strPrefix, pattern);
   }
   
   /**
    * Configure the patterns that the mapper should use when mapping the data 
    * from plain text to POJO representation.
    */
   protected abstract void configure();
}
