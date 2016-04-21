<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Liste des adhérents</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Tableau des adhérents
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover" id="datas" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>Numéro</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Ville</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Numéro</th>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Ville</th>
                                <th>Action</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${mesAdherents}" var="item">
                                <tr>
                                    <td>${item.idAdherent}</td>
                                    <td>${item.nomAdherent}</td>
                                    <td>${item.prenomAdherent}</td>
                                    <td>${item.villeAdherent}</td>
                                    <td><a href="adherent?action=modif&adherent=${item.idAdherent}"><i class="fa fa-edit"></i></a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<%@ include file="../footer.jsp" %>
