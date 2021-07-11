import { FaBell } from 'react-icons/fa';

export function Alerts (){

    return (
        <div className="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <nav className="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                <div className="container-fluid"><button className="btn btn-link d-md-none rounded-circle me-3" id="sidebarToggleTop" type="button"><i className="fas fa-bars"></i></button>
                    <ul className="navbar-nav flex-nowrap ms-auto">
                        <li className="nav-item dropdown no-arrow mx-1">
                            <div className="nav-item dropdown no-arrow"><a className="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><span className="badge bg-danger badge-counter">3+</span><FaBell/></a>
                                <div className="dropdown-menu dropdown-menu-end dropdown-list animated--grow-in">
                                    <h6 className="dropdown-header">alerts center</h6>
                                    <a className="dropdown-item d-flex align-items-center" href="#">
                                        <div className="me-3">
                                            <div className="bg-primary icon-circle"></div>
                                        </div>
                                        <div><span className="small text-gray-500">December 12, 2019</span>
                                            <p>A new monthly report is ready to download!</p>
                                        </div>
                                    </a>
                                    <a className="dropdown-item d-flex align-items-center" href="#">
                                        <div className="me-3">
                                            <div className="bg-success icon-circle"></div>
                                        </div>
                                        <div><span className="small text-gray-500">December 7, 2019</span>
                                            <p>$290.29 has been deposited into your account!</p>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>  
            </nav>  
        </div>   
    </div>
    )
}