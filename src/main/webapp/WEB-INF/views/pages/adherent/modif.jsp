<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Modification d'un adhérent</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Veuillez saisir les données suivantes :
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form name="identification" method="post" action="/adherent/modifier" role="form">
                                <div class="form-group">
                                    <label>Nom</label>
                                    <input name="txtnom" class="form-control" value="${adherent.prenomAdherent}">
                                    <p class="help-block">Prénom de l'adhérent</p>
                                </div>
                                <div class="form-group">
                                    <label>Prénom</label>
                                    <input name="txtprenom" class="form-control" value="${adherent.nomAdherent}">
                                    <p class="help-block">Nom de l'adhérent</p>
                                </div>
                                <div class="form-group">
                                    <label>Ville</label>
                                    <input name="txtville" class="form-control" value="${adherent.villeAdherent}">
                                    <p class="help-block">Ville de l'adhérent</p>
                                </div>
                                <input name="txtid" type="hidden" value="${adherent.idAdherent}">
                                <button type="submit" class="btn btn-default">Modifier</button>
                                <a href="/index.jsp" class="btn btn-default">Annuler</a>
                            </form>
                        </div>
                    </div>
                    <!-- /.row (nested) -->
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

