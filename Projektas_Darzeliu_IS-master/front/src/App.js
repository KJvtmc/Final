import React, { useEffect, useReducer } from "react";
import { Switch, Route, Redirect } from "react-router-dom";

import "./index.css";
import "./App.css";
import Spinner from "./components/08CommonComponents/Spinner";
import swal from "sweetalert";

import Login from "./components/01Login/LoginContainer";
import NotFound from "./components/03NotFound/NotFound";
import Admin from "./components/04Admin/AdminContainer";
import UserListContainer from "./components/04Admin/UserListContainer";
import KindergartenContainer from "./components/05Kindergarten/KindergartenContainer";
import UpdateProfileFormContainer from "./components/06UpdateProfile/UpdateProfileFormContainer";
import CreateCompensationApplicationFormContainer from "./components/17CompensationApplication/CreateCompensationApplicationFormContainer";
import AdminNavBar from "./components/00Navigation/AdminNavBar";
import UserNavBar from "./components/00Navigation/UserNavBar";

import AuthContext from "./components/11Context/AuthContext";
import http from "./components/10Services/httpService";
import CommonErrorHandler from "./components/10Services/CommonErrorHandler";
import apiEndpoint from "./components/10Services/endpoint";
import { UserHomeContainer } from "./components/02Main/UserHomeContainer";
import EventJournalContainer from "./components/14EventJournal/EventJournalContainer";
import CompensationApplicationListContainer from "./components/17CompensationApplication/CompensationApplicationListContainer";
import NewAccountContainer from "./components/01Login/NewAccountContainer";
import ItemContainer from "./components/Items/ItemContainer"
import GroupsContainer from "./components/Groups/GroupsContainer"
import BookList from "./components/0UserPages/BookList"
import MyBookList from "./components/0UserPages/MyBookList"
var initState = {
  isAuthenticated: null,
  username: null,
  role: null,
  error: null,
};

const reducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      return {
        ...state,
        isAuthenticated: true,
        username: action.payload.username,
        role: action.payload.role,
        error: null,
      };
    case "LOGOUT":
      return {
        ...state,
        isAuthenticated: false,
        username: null,
        role: null,
        error: null,
      };
    case "ERROR":
      return {
        ...state,
        isAuthenticated: false,
        username: null,
        role: null,
        error: action.payload,
      };
    default:
      return state;
  }
};

function App() {
  const [state, dispatch] = useReducer(reducer, initState);

  useEffect(() => {

    if (state.isAuthenticated === null) {
      http
        .get(`${apiEndpoint}/api/loggedUserRole`)
        .then((resp) => {
          dispatch({
            type: "LOGIN",
            payload: { role: resp.data },
          });
        })
        .catch((error) => {
          const unexpectedError = error.response &&
            error.response.status >= 400 &&
            error.response.status < 500;

          if (!unexpectedError || (error.response && error.response.status === 404)) {
            swal("Įvyko klaida, puslapis nurodytu adresu nepasiekiamas");
            dispatch({
              type: "ERROR",
            });
          }
          else dispatch({
            type: "ERROR",
            payload: error.response.status,
          });
        });
    }
  }, [state.isAuthenticated]);

  if (state.isAuthenticated) {
    switch (state.role) {
      case "ADMIN":
        return (
          <AuthContext.Provider value={{ state, dispatch }}>
            <CommonErrorHandler>
              <div className="container-fluid px-0">
                <AdminNavBar>
                  <Switch>
                    <Route exact path="/" component={Admin} />
                    <Route exact path="/home" component={Admin} />
                    <Route exact path="/admin" component={Admin} />
                    <Route
                      exact
                      path="/naudotojai"
                      component={UserListContainer}
                    />
                    <Route
                      exact
                      path="/zurnalas"
                      component={EventJournalContainer}
                    />
                    <Route
                      exact
                      path="/profilis/atnaujinti"
                      component={UpdateProfileFormContainer}
                    />
                    <Route
                      exact
                      path="/tiekejai"
                      component={KindergartenContainer}
                    />
                    <Route
                      exact
                      path="/produktai"
                      component={ItemContainer}
                    />
                    <Route
                      exact
                      path="/grupes"
                      component={GroupsContainer}
                    />

                    <Route
                      exact
                      path="/prasymai"
                      component={CompensationApplicationListContainer}
                    />
                    <Route
                      exact
                      path="/profilis/atnaujinti"
                      component={UpdateProfileFormContainer}
                    />
                    <Route path="*" component={NotFound} />
                  </Switch>
                </AdminNavBar>
              </div>
            </CommonErrorHandler>
          </AuthContext.Provider>
        );
      case "USER":
        return (
          <AuthContext.Provider value={{ state, dispatch }}>
            <CommonErrorHandler>
              <div className="container-fluid px-0">
                <UserNavBar>
                  <Switch>
                    <Route exact path="/" component={UserHomeContainer} />
                    <Route exact path="/home" component={UserHomeContainer} />
                    <Route
                      exact
                      path="/prasymai"
                      component={UserHomeContainer}
                    />
                    <Route
                      exact
                      path="/knygos"
                      component={BookList}
                    />
                    <Route
                      exact
                      path="/manoknygos"
                      component={MyBookList}
                    />
                    <Route
                      exact
                      path="/profilis/atnaujinti"
                      component={UpdateProfileFormContainer}
                    />
                    <Route path="*" component={NotFound} />
                  </Switch>
                </UserNavBar>
              </div>
            </CommonErrorHandler>
          </AuthContext.Provider>
        );
      default:
        return (
          <AuthContext.Provider value={{ state, dispatch }}>
            <div className="container-fluid px-0">
              <NotFound />
            </div>
          </AuthContext.Provider>
        );
    }
  } else if (state.isAuthenticated === false) {
    return (
      <div>
        <AuthContext.Provider value={{ state, dispatch }}>
          <Switch>
            <Route exact path="/login" component={Login} />
            <Route
              exact
              path="/newAccount"
              component={NewAccountContainer}
            />
            <Route path="*">
              <Redirect to="/login" />
            </Route>
          </Switch>
        </AuthContext.Provider>
      </div>
    );
  }
  else return <Spinner />;
}

export default App;
