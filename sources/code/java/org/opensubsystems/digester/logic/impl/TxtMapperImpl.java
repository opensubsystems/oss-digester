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
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.logic.impl.StatelessControllerImpl;
import org.opensubsystems.core.util.ClassFactory;
import org.opensubsystems.core.util.Log;
import org.opensubsystems.core.util.ThreeElementStruct;
import org.opensubsystems.core.util.TwoElementStruct;
import org.opensubsystems.digester.data.Record;
import org.opensubsystems.digester.logic.Mapper;
import org.opensubsystems.digester.logic.Parser;

/**
 * Implementation of the mapper interface for the data in plain text format 
 * modeled after the commons digester package.
 *
 * @author bastafidli
 */
public abstract class TxtMapperImpl<C extends DigesterContext, 
                                    R extends Record,
                                    O> 
                                   extends StatelessControllerImpl
                                   implements Mapper<C, R, O>
{
   // Attributes ///////////////////////////////////////////////////////////////
   
   /**
    * Map containing patterns that the mapper uses to map data in the plain text
    * format to POJO objects.
    */
   protected Map<String, ThreeElementStruct<String, String, Parser>> m_mpPatters;
   
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
 
   /**
    * {@inheritDoc}
    */
   @Override
   public TwoElementStruct<O, Record> create(
      C context,
      R record
   ) throws OSSException
   {
      O                                          result = null;
      R                                          leftover = record;
      R                                          element;
      ThreeElementStruct<String, String, Parser> value;
      String                                     strPrefix;
      String                                     strDelimiter;
      Parser                                     parser;
      TwoElementStruct<R, R>                     split;
      
      for (Map.Entry<String, ThreeElementStruct<String, String, Parser>> entry 
           : m_mpPatters.entrySet())
      {
         value = entry.getValue();
         strPrefix = value.getFirst();
         strDelimiter = value.getSecond();
         parser = value.getThird();
         
         if (record.startsWith(strPrefix))
         {
            split = record.split(strDelimiter, Record.Presence.REQUIRED);
            element = split.getFirst();
            leftover = split.getSecond();
            result = (O)parser.parse(context, element);
            break;
         }
      }
      
      // TODO: We still need to map the parsed data to a hava object
      
      return new TwoElementStruct<O, Record>(result, leftover);
   }
   
   // Helper methods ///////////////////////////////////////////////////////////

   /**
    * Configure patter for specified set of attributes.
    * 
    * @param strPrefix - prefix that the record has to start with to match the
    *                    pattern
    * @param strDelimiter - delimiter used to separate the record starting with
    *                       the given prefix from the rest of the data
    * @param clsParser - parser use to parse the record to a a set of attributes
    * @throws OSSException - an error has occurred
    */
   protected void forPrefixAndDelimiter(
      String strPrefix,
      String strDelimiter,
      // TODO: Improve: This should be limited to classes which implements Parser interface
      Class  clsParser
   ) throws OSSException
   {
      ThreeElementStruct<String, String, Parser> pattern;
      ClassFactory<Parser>                       factory;
      Parser                                     parser;
      
      factory = new ClassFactory<>(clsParser);
      parser = factory.createInstance();
      pattern = new ThreeElementStruct<>(strPrefix, strDelimiter, parser);
      
      m_mpPatters.put(strPrefix, pattern);
   }
   
   /**
    * Configure the patterns that the mapper should use when mapping the data 
    * from plain text to POJO representation.
    * 
    * @throws OSSException - an error has occurred
    */
   protected abstract void configure(
   ) throws OSSException;
}
