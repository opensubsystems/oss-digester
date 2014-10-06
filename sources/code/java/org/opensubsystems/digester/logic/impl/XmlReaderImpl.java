/*
 * Copyright (C) 2003 - 2014 OpenSubsystems.com/net/org and its owners. All rights reserved.
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

import org.opensubsystems.digester.logic.OSSReader;
import org.opensubsystems.digester.data.DigesterContext;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.binder.RulesModule;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.error.OSSMultiException;
import org.opensubsystems.core.logic.impl.StatelessControllerImpl;
import org.opensubsystems.core.util.GlobalConstants;
import org.opensubsystems.core.util.Log;
import org.opensubsystems.digester.data.Record;
import org.xml.sax.SAXException;

/**
 * Implementation of the reader interface using the commons digester package.
 *
 * @author bastafidli
 */
public class XmlReaderImpl<R extends Record> extends StatelessControllerImpl
                                             implements OSSReader<R>
{
   // Attributes ///////////////////////////////////////////////////////////////
   
   /**
    * Rules describing how to digest the XML input into Java objects.
    */
   RulesModule m_rules;
   
   // Cached values ////////////////////////////////////////////////////////////

   /**
    * Logger for this class
    */
   private static Logger s_logger = Log.getInstance(XmlReaderImpl.class);

   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Default constructor. The rules modules has to be initialized using context.
    */
   public XmlReaderImpl()
   {
      // Do nothing
      // TODO: The rules module has to be supplied via the context
   }
   
   /**
    * Constructor explicitly specifying rule module to use.
    * 
    * @param rules - Rules describing how to digest the XML input into Java 
    *                objects.
    */
   public XmlReaderImpl(
      RulesModule rules
   )
   {
      m_rules = rules;
   }
   
   // Logic ////////////////////////////////////////////////////////////////////
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void open(
      DigesterContext context,
      Reader          source,
      String          strSourceName
   ) throws OSSException
   {
      if (GlobalConstants.ERROR_CHECKING)
      {
         assert m_rules != null : 
                "No rules modules configured to digest data from " 
                + strSourceName; 
      }
      context.setOpenedSource(source, strSourceName);
      s_logger.log(Level.FINE, "Opened reader to read from {0}", strSourceName);
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public R read(
      DigesterContext context
   ) throws OSSException
   {
      R        record = null;
      Digester digester;
      Reader   source;
      String   strSourceName;

      source = context.getOpenedSource();
      strSourceName = context.getOpenedSourceName();
      if (GlobalConstants.ERROR_CHECKING)
      {
         assert source != null : 
                "No opened source is available to read from."; 
      }

      try
      {
         digester = DigesterLoader.newLoader(m_rules).newDigester();
         s_logger.log(Level.FINE, "Reading data from {0}", strSourceName);
         record = digester.parse(source);
         s_logger.log(Level.FINE, "Finished reading data from {0}", strSourceName);
      }
      catch (SAXException | IOException exc)
      {
         throw new OSSMultiException("Error reading data from " 
                                     + strSourceName, exc);
      }
      
      return record;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void close(
      DigesterContext context
   ) throws OSSException
   {
      String strSourceName;
      
      strSourceName = context.getOpenedSourceName();
      context.setOpenedSource(null, "");
      s_logger.log(Level.FINE, "Closed reader to read from {0}", strSourceName);
   }
}
