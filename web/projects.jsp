<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="t" %>
<!DOCTYPE HTML>
<html>
<head>
    <t:Head title="Projects - Developer Sam"/>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <t:Header title="Projects - Developer Sam" selected="2"/>
    <main class="mdl-layout__content">
        <t:Card title="DevSALON">
            <t:CardMedia src="resource/icon/devsalon.png" alt="DevSALON"/>
            <t:CardText>
                Developer Sam's Association of Loosely Organized Nerds
            </t:CardText>
            <t:CardActions>
                <t:LinkButton href="https://devsalon.github.io/" openInNewTab="true">View on GitHub</t:LinkButton>
            </t:CardActions>
        </t:Card>
        <t:Card title="SAM">
            <t:CardText>An online platform for homework assignment.</t:CardText>
            <t:CardActions>
                <t:LinkButton href="https://github.com/SamChou19815/SAM" openInNewTab="true">
                    Project on GitHub
                </t:LinkButton>
            </t:CardActions>
        </t:Card>
        <t:Card title="Matrix (not available)">
            <t:CardText>A program to analyze social network.</t:CardText>
            <t:CardActions>
                <t:LinkButton href="resource/papers/EvolutionandAssimilation.pdf" openInNewTab="true">
                    Latest Paper
                </t:LinkButton>
            </t:CardActions>
        </t:Card>
        <t:Card title="Small Projects">
            <t:CardText>Some of the small programming projects I developed and open-sourced.</t:CardText>
            <t:CardActions>
                <t:LinkButton href="https://github.com/SamChou19815/Snake-Game" openInNewTab="true">
                    Snake
                </t:LinkButton>
                <t:LinkButton href="https://github.com/SamChou19815/TEN-Board-Game" openInNewTab="true">
                    TEN
                </t:LinkButton>
                <t:LinkButton href="https://github.com/SamChou19815/Bots-Civ" openInNewTab="true">
                    Bots Civ
                </t:LinkButton>
            </t:CardActions>
        </t:Card>
        <t:Card title="Computer Models">
            <t:CardText>Some of the computer models I developed.</t:CardText>
            <t:CardActions>
                <t:LinkButton href="resource/papers/CollegeAdmissionNN.pdf" openInNewTab="true">
                    College Admission Decision (Overall)
                </t:LinkButton>
                <t:LinkButton href="resource/papers/AcademicsCollegeAdmission.pdf" openInNewTab="true">
                    College Admission Decision (Academics)
                </t:LinkButton>
            </t:CardActions>
        </t:Card>
    </main>
</div>
</body>
</html>

