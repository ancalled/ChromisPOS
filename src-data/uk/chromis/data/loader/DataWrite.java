//    Chromis POS  - The New Face of Open Source POS
//    Copyright (c) 2015 
//    http://www.chromis.co.uk
//
//    This file is part of Chromis POS
//
//     Chromis POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Chromis POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>.

package uk.chromis.data.loader;

import uk.chromis.basic.BasicException;

/**
 *
 * @author  adrian
 */
public interface DataWrite {
 
    /**
     *
     * @param paramIndex
     * @param iValue
     * @throws BasicException
     */
    public void setInt(int paramIndex, Integer iValue) throws BasicException;
    /**
     *
     * @param paramIndex
     * @param sValue
     * @throws BasicException
     */
    public void setString(int paramIndex, String sValue) throws BasicException;

    /**
     *
     * @param paramIndex
     * @param dValue
     * @throws BasicException
     */
    public void setDouble(int paramIndex, Double dValue) throws BasicException;

    /**
     *
     * @param paramIndex
     * @param bValue
     * @throws BasicException
     */
    public void setBoolean(int paramIndex, Boolean bValue) throws BasicException;

    /**
     *
     * @param paramIndex
     * @param dValue
     * @throws BasicException
     */
    public void setTimestamp(int paramIndex, java.util.Date dValue) throws BasicException;
    
    //public void setBinaryStream(int paramIndex, java.io.InputStream in, int length) throws DataException;
    
    /**
     *
     * @param paramIndex
     * @param value
     * @throws BasicException
     */
        public void setBytes(int paramIndex, byte[] value) throws BasicException;    

    /**
     *
     * @param paramIndex
     * @param value
     * @throws BasicException
     */
    public void setObject(int paramIndex, Object value) throws BasicException;
}
