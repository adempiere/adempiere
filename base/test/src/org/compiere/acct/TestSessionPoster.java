/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.acct;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.util.CLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("Accounting")
@Tag("AcctProcessor")
@Tag("SessionPoster")
@Tag("ClientAcctProcessor")
@DisplayName("TestSessionPoster: Given the SessionPoster")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TestSessionPoster extends CommonUnitTestSetup {

    @Nested
    @DisplayName("When the post on a document throws exception")
    class whenPostThrowsException {
        
        SessionPoster posterMock;
        Doc docMock;
        
        @BeforeEach
        void setupMocksToThrowException() {

            CLogger logMock = mock(CLogger.class);
            doNothing().when(logMock).log(any(), anyString(), any(Throwable.class));
            
            docMock = mock(Doc.class);
            when(docMock.get_TableName())
                    .thenReturn("SomeTable");
    
            posterMock = mock(SessionPoster.class);
            doCallRealMethod().when(posterMock)
                    .postDocumentInItsOwnTransaction(anyInt(), anyInt());
            doReturn(docMock).when(posterMock).getDoc(anyInt(), anyInt(),
                    anyString());
            doThrow(new AdempiereException("Thrown on purpose for testing")).when(posterMock)
                    .postDoc(any());
            posterMock.log = logMock;
            doNothing().when(posterMock).savePostedStatus(any(), any(),
                    any());

        }
        
        @Test
        @DisplayName("Then postDocumentInItsOwnTransaction returns false")
        final void whenPostThrowsException_returnsFalse() {
    
            assertFalse(posterMock.postDocumentInItsOwnTransaction(1, 1));
    
        }
        
        @Test
        @DisplayName("Then the doc is saved with Doc.STATUS_NotPosted using the null transaction")
        final void whenPostThrowsException_docIsSavedAsNotPosted() {
    
            posterMock.postDocumentInItsOwnTransaction(1, 1);
            verify(posterMock).savePostedStatus(docMock,
                    Doc.STATUS_NotPosted, null);
    
        }

    }
}
