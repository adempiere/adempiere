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
package org.compiere.model;

import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_Activated;
import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_Depreciated;
import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_Disposed;
import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_New;
import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_Preservation;
import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_Retired;
import static org.compiere.FA.model.X_A_Asset.A_ASSET_STATUS_Sold;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.FA.model.MAsset;
import org.compiere.FA.model.MAssetGroup;
import org.compiere.util.CLogger;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TestMAsset extends CommonUnitTestSetup {

    static Timestamp today = TimeUtil.getDay(System.currentTimeMillis());

    @Captor
    ArgumentCaptor<Integer> idCaptor;

    @Captor
    ArgumentCaptor<Timestamp> dateCaptor;

    @Test
    void testSetAssetGroup() {

        MAsset assetMock = mock(MAsset.class);
        doCallRealMethod().when(assetMock)
                .setAssetGroup(any(MAssetGroup.class));

        MAssetGroup assetGroupMock = mock(MAssetGroup.class);
        when(assetGroupMock.getA_Asset_Group_ID()).thenReturn(1);

        assetMock.setAssetGroup(assetGroupMock);

        verify(assetMock).setA_Asset_Group_ID(idCaptor.capture());

        assertEquals(1, idCaptor.getValue(),
                "SetAssetGroup did not set the correct ID");

    }

    @Test
    void getDownloadNames_whenNull() {

        MAsset assetMock = mock(MAsset.class);
        doCallRealMethod().when(assetMock).getDownloadNames();
        when(assetMock.getProductDownloads()).thenReturn(null);

        String[] names = assetMock.getDownloadNames();
        assertEquals(0, names.length);

    }

    @Test
    void getDownloadNames_shouldReturnName() {

        MProductDownload downloadMock = mock(MProductDownload.class);
        when(downloadMock.getName()).thenReturn("name");
        MProductDownload[] dls = { downloadMock };

        CLogger logMock = mock(CLogger.class);
        MAsset assetMock = mock(MAsset.class);
        assetMock.log = logMock;
        doCallRealMethod().when(assetMock).getDownloadNames();
        when(assetMock.getProductDownloads()).thenReturn(dls);

        String[] names = assetMock.getDownloadNames();
        assertEquals("name", names[0]);

    }

    @Test
    void getDownloadURLs_whenNull() {

        MAsset assetMock = mock(MAsset.class);
        doCallRealMethod().when(assetMock).getDownloadURLs();
        when(assetMock.getDownloadURLs()).thenReturn(null);

        String[] urls = assetMock.getDownloadURLs();
        assertEquals(0, urls.length);

    }

    @Test
    void getDownloadURLs_shouldReturnLastElementOfURL() {

        MProductDownload downloadMock1 = mock(MProductDownload.class);
        MProductDownload downloadMock2 = mock(MProductDownload.class);
        when(downloadMock1.getDownloadURL())
                .thenReturn("http://someurl.com/name");
        when(downloadMock2.getDownloadURL())
                .thenReturn("file:\\\\directory\\fileName");
        MProductDownload[] dls = { downloadMock1, downloadMock2 };

        CLogger logMock = mock(CLogger.class);
        MAsset assetMock = mock(MAsset.class);
        assetMock.log = logMock;
        doCallRealMethod().when(assetMock).getDownloadURLs();
        when(assetMock.getProductDownloads()).thenReturn(dls);

        String[] urls = assetMock.getDownloadURLs();
        assertEquals(2, urls.length);
        assertEquals("name", urls[0]);
        assertEquals("fileName", urls[1]);

    }

    static Stream<Arguments> statusProvider() {

        return Stream.of(

                // status, processed, disposed, fullyDep, dep
                arguments(A_ASSET_STATUS_Activated, true, false, false),
                arguments(A_ASSET_STATUS_Disposed, true, true, false),
                arguments(A_ASSET_STATUS_Depreciated, true, false, true),
                arguments(A_ASSET_STATUS_New, false, false, false),
                arguments(A_ASSET_STATUS_Preservation, true, false, false),
                arguments(A_ASSET_STATUS_Retired, true, false, false),
                arguments(A_ASSET_STATUS_Sold, true, false, false)

        );

    }

    @Captor
    ArgumentCaptor<Boolean> processedCaptor;
    @Captor
    ArgumentCaptor<Boolean> disposedCaptor;
    @Captor
    ArgumentCaptor<Boolean> fullyDepCaptor;

    @ParameterizedTest
    @MethodSource("statusProvider")
    void updateStatus_whenNew(String status, boolean processed,
            boolean disposed, boolean fullyDep) {

        MAsset assetMock = mock(MAsset.class);
        doCallRealMethod().when(assetMock).updateStatus();
        when(assetMock.getA_Asset_Status()).thenReturn(status);
        when(assetMock.isFullyDepreciated()).thenReturn(fullyDep);
        when(assetMock.getAssetServiceDate()).thenReturn(today);

        assetMock.updateStatus();

        verify(assetMock).setProcessed(processedCaptor.capture());
        verify(assetMock).setIsDisposed(disposedCaptor.capture());
        verify(assetMock).setIsFullyDepreciated(fullyDepCaptor.capture());
        if (fullyDep || disposed)
            verify(assetMock).setIsDepreciated(false);
        else
            verify(assetMock, never()).setIsDepreciated(anyBoolean());
        verify(assetMock).setAssetActivationDate(dateCaptor.capture());

        assertEquals(processed, processedCaptor.getValue());
        assertEquals(disposed, disposedCaptor.getValue());
        assertEquals(fullyDep, fullyDepCaptor.getValue());
        assertEquals(today, dateCaptor.getValue());

    }

}
