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

import org.opensubsystems.digester.data.DigesterContext;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.logic.StatelessController;
import org.opensubsystems.digester.data.Record;

/**
 * Reader capable of reading content from a source and making it available in the 
 * form of records.
 *
 * @author bastafidli
 */
public interface Reader<S, C extends DigesterContext, R extends Record> extends StatelessController
{
   /**
    * Open the source for reading.
    * 
    * @param context - context within which the content will be read
    * @param source - source from this the reader should read data
    * @throws OSSException - an error has occurred 
    */
   void open(
      C context,
      S source
   ) throws OSSException;
   
   /**
    * Read record from the source initialized during the open call.
    *
    * @param context - context within which the content is read
    * @return R - record read by the reader or null if there are no more data to
    *             read.
    * @throws OSSException - an error has occurred 
    */
   R read(
      C context
   ) throws OSSException;

   /**
    * Close the previously opened context and cleanup any resources allocated 
    * during its use. This operation must be called once open was called and 
    * succeeded.
    * 
    * @param context - context initialized during open
    */
   void close(
      C context
   ) throws OSSException;
}
